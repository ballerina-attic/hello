/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinax.hello;

import org.ballerinalang.compiler.plugins.AbstractCompilerPlugin;
import org.ballerinalang.compiler.plugins.SupportedAnnotationPackages;
import org.ballerinalang.model.elements.PackageID;
import org.ballerinalang.model.tree.AnnotationAttachmentNode;
import org.ballerinalang.model.tree.ServiceNode;
import org.ballerinalang.util.diagnostic.Diagnostic;
import org.ballerinalang.util.diagnostic.DiagnosticLog;
import org.wso2.ballerinalang.compiler.tree.BLangAnnotationAttachment;
import org.wso2.ballerinalang.compiler.tree.expressions.BLangRecordLiteral;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Compiler plugin to generate greetings.
 */
@SupportedAnnotationPackages(
        // Tell compiler we are only interested in ballerinax.hello annotations.
        value = "ballerinax.hello"
)
public class HelloPlugin extends AbstractCompilerPlugin {
    private DiagnosticLog dlog;


    @Override
    public void init(DiagnosticLog diagnosticLog) {
        // Initialize the logger.
        this.dlog = diagnosticLog;
    }

    @Override
    public void process(ServiceNode serviceNode, List<AnnotationAttachmentNode> annotations) {
        //Iterate through the annotation Attachment Nodes
        for (AnnotationAttachmentNode attachmentNode : annotations) {
            List<BLangRecordLiteral.BLangRecordKeyValue> keyValues =
                    ((BLangRecordLiteral) ((BLangAnnotationAttachment) attachmentNode).expr).getKeyValuePairs();
            //Iterate through the annotations
            for (BLangRecordLiteral.BLangRecordKeyValue keyValue : keyValues) {
                String annotationValue = keyValue.getValue().toString();
                //Match annotation key and assign the value
                switch (keyValue.getKey().toString()) {
                    case "salutation":
                        HelloModel.getInstance().setGreeting(annotationValue);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void codeGenerated(PackageID packageID, Path binaryPath) {
        //extract file name.
        String filePath = binaryPath.toAbsolutePath().toString().replace(".balx", ".txt");
        String greeting = HelloModel.getInstance().getGreeting();
        try {
            writeToFile(greeting, filePath);
        } catch (IOException e) {
            dlog.logDiagnostic(Diagnostic.Kind.ERROR, null, e.getMessage());
        }
    }

    /**
     * Write content to a File. Create the required directories if they don't not exists.
     *
     * @param context        context of the file
     * @param targetFilePath target file path
     * @throws IOException If an error occurs when writing to a file
     */
    public void writeToFile(String context, String targetFilePath) throws IOException {
        File newFile = new File(targetFilePath);
        // append if file exists
        if (newFile.exists()) {
            Files.write(Paths.get(targetFilePath), context.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
            return;
        }
        //create required directories
        if (newFile.getParentFile().mkdirs()) {
            Files.write(Paths.get(targetFilePath), context.getBytes(StandardCharsets.UTF_8));
            return;
        }
        Files.write(Paths.get(targetFilePath), context.getBytes(StandardCharsets.UTF_8));
    }
}
